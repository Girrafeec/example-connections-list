package com.girrafeecstud.example_connections_list.connections_list.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.core_components_api.MainViewModelFactory
import com.girrafeecstud.core_components_api.openAppSettings
import com.girrafeecstud.core_components_api.openGPSSettings
import com.girrafeecstud.core_ui.R
import com.girrafeecstud.core_ui.extension.loadAndSetImage
import com.girrafeecstud.core_ui.extension.removeView
import com.girrafeecstud.core_ui.extension.showView
import com.girrafeecstud.core_ui.ui.ActionDialog
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.core_ui.ui.PermissionDialog
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import com.girrafeecstud.example_connections_list.connections_list.databinding.FragmentConnectionsWithDistanceListBinding
import com.girrafeecstud.example_connections_list.connections_list.presentation.ConnectionsContract
import com.girrafeecstud.example_connections_list.connections_list.presentation.ConnectionsListComponentViewModel
import com.girrafeecstud.example_connections_list.connections_list.presentation.ConnectionsWithDistanceViewModel
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConnectionsWithDistanceListFragment :
    BaseFragment(),
    ConnectionClickEvent {

    private var _binding: FragmentConnectionsWithDistanceListBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var connectionsAdapter: ConnectionsWithDistanceAdapter

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val connectionsWithDistanceViewModel: ConnectionsWithDistanceViewModel by viewModels {
        viewModelFactory
    }

    private val locationPermissionsRequestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.forEach{ permission ->
                if (permission.key != Manifest.permission.ACCESS_FINE_LOCATION)
                    return@forEach
                connectionsWithDistanceViewModel.respondLocationPermissionRequest(
                    isPermissionGranted = permission.value
                )
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this)[ConnectionsListComponentViewModel::class.java]
            .get()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConnectionsWithDistanceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecView()
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                connectionsWithDistanceViewModel.container.stateFlow
                    .onEach {
                        handleState(it)
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)

                connectionsWithDistanceViewModel.container.sideEffectFlow
                    .onEach {
                        handlePostSideEffect(it)
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    override fun setListeners() {
        binding.pinnedConnectionContainer.setOnClickListener {
            connectionsWithDistanceViewModel.unpinConnection()
        }
    }

    override fun onConnectionClicked(connection: Connection) {
        // Only one item may be pinned
        if (binding.pinnedConnectionContainer.isVisible)
            return
        connectionsWithDistanceViewModel.pinConnection(connection = connection)
    }

    private fun handleState(state: ConnectionsContract.ConnectionsWithDistanceState) {

        state.connectionsWithDistance?.let {
            connectionsAdapter.updateConnections(it)
        }

        state.isLoading.let { isLoading ->
            when (isLoading) {
                true -> {
                    binding.progressBar.showView()
                    binding.conenctionsWithDistance.removeView()
                }
                false -> {
                    binding.progressBar.removeView()
                    binding.conenctionsWithDistance.showView()
                }
            }
        }

        state.isConnectionPinned.let { isPinned ->
            when (isPinned) {
                true -> {
                    state.pinnedConnection?.let { connection ->
                        binding.pinnedConnectionContainer.showView()
                        binding.connectionProfileImage.loadAndSetImage(connection.profileImageUrl)
                        binding.connectionName.text = requireContext().resources.getString(
                            R.string.user_first_last_name,
                            connection.firstName,
                            connection.lastName
                        )
                        connectionsAdapter.removePinnedConnection(connectionId = connection.connectionId)
                        connectionsWithDistanceViewModel.getConnectionsWithDistanceToChosenConnection(
                            chosenConnection = connection
                        )
                    }
                }
                false  -> {
                    binding.pinnedConnectionContainer.removeView()
                    connectionsWithDistanceViewModel.getConnectionsWithDistanceToMe()
                }
            }
        }
    }

    private fun handlePostSideEffect(effect: ConnectionsContract.ConnectionsWithDistanceSideEffect) {
        when (effect) {
            ConnectionsContract.ConnectionsWithDistanceSideEffect.EnableGPSDialog -> {
                val enableLocationTitle = requireContext().resources
                    .getString(com.girrafeecstud.location_api.R.string.turn_on_location_title)
                val enableLocationMessage = requireContext().resources
                    .getString(com.girrafeecstud.example_connections_list.connections_list.R.string.enable_location_for_connections)
                val enableGpsDialog = ActionDialog.Builder(context = requireContext())
                    .setTitle(enableLocationTitle)
                    .setMessage(enableLocationMessage)
                    .setOnPositiveClick { requireActivity().openGPSSettings() }
                    .build()

                enableGpsDialog.show()
            }
            ConnectionsContract.ConnectionsWithDistanceSideEffect.RequestLocationPermissionsDialog -> {
                requestLocationPermission()
            }
            ConnectionsContract.ConnectionsWithDistanceSideEffect.RequestLocationPermissionRationaleDialog -> {
                val locationPermissionDialog = PermissionDialog.Builder(context = requireContext())
                    .setPermissionTextProvider(LocationPermissionTextProvider())
                    .setIsPermanentlyDeclined(
                        !shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
                    )
                    .setOnOkClick { requestLocationPermission() }
                    .setOnGoToAppSettingsClick { requireActivity().openAppSettings() }
                    .build()

                locationPermissionDialog.show()
            }
        }
    }

    private fun initRecView() {
        binding.conenctionsWithDistance.let {
            connectionsAdapter.clickEvent = this
            it.adapter = connectionsAdapter
            it.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            it.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun requestLocationPermission() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        locationPermissionsRequestLauncher.launch(
            permissions
        )
    }
}