/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_list.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.core_ui.ui.BaseFlowFragment
import com.girrafeecstud.example_connections_list.connections_list.R
import com.girrafeecstud.example_connections_list.connections_list.databinding.FragmentConnectionsListFlowBinding
import com.girrafeecstud.example_connections_list.connections_list.di.component.ConnectionsListFeatureComponent

class ConnectionsListFlowFragment : BaseFlowFragment(
    R.id.connections_fragment_container
) {

    private var _binding: FragmentConnectionsListFlowBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ConnectionsListFeatureComponent.get().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConnectionsListFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUpNavigation() {

    }

    override fun setListeners() {

    }

    override fun registerObservers() {

    }
}