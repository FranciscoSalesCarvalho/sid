package com.francisco.sid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.francisco.sid.data.model.Event
import com.francisco.sid.databinding.FragmentEventsListBinding
import com.francisco.sid.ui.adapter.EventsAdapter
import com.francisco.sid.ui.adapter.listener.EventsListener
import com.francisco.sid.viewmodel.EventsViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.maintenance_page.view.*
import javax.inject.Inject

class EventsListFragment : DaggerFragment(), EventsListener {

    private lateinit var binding: FragmentEventsListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: EventsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(EventsViewModel::class.java)
    }

    private val adapter by lazy {
        EventsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsListBinding.inflate(layoutInflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        configRetryButton()
        binding.eventsRecyclerView.adapter = adapter

        viewModel.fetchEvents()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.events.observe(viewLifecycleOwner, { state ->
            state.handleIt(
                success = {
                    binding.maintenancePage.visibility = View.GONE
                    binding.eventsRecyclerView.visibility = View.VISIBLE
                    it.let {
                        adapter.submitList(it)
                    }
                },
                exception = {
                    binding.eventsRecyclerView.visibility = View.GONE
                    binding.maintenancePage.visibility = View.VISIBLE
                }
            )
        })
    }

    override fun onClick(event: Event) {
        Navigation.findNavController(requireView())
            .navigate(
                EventsListFragmentDirections.actionEventsListFragmentToEventDetailsFragment(
                    event
                )
            )
    }

    private fun configRetryButton() {
        binding.maintenancePage.btnRetry.setOnClickListener {
            viewModel.fetchEvents()
        }
    }
}