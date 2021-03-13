package com.francisco.sid.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.francisco.sid.R
import com.francisco.sid.data.model.Event
import com.francisco.sid.databinding.FragmentEventDetailsBinding
import dagger.android.support.DaggerFragment

class EventDetailsFragment: DaggerFragment() {

    private lateinit var binding: FragmentEventDetailsBinding

    private val event: Event by lazy {
        arguments?.let {
            EventDetailsFragmentArgs.fromBundle(it).event
        } ?: throw ExceptionInInitializerError()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDetailsBinding.inflate(layoutInflater)

        binding.lifecycleOwner = this
        binding.event = event

        configToolbar()
        configCheckinButton()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.share -> sharePost()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configToolbar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = event.title
    }

    private fun sharePost() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "${event.image}\n\n${event.description}")
        startActivity(Intent.createChooser(shareIntent, "Share thumbnail"))
    }

    private fun configCheckinButton() {
        binding.btnGoCheckin.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(EventDetailsFragmentDirections.actionEventDetailsFragmentToCheckinFormFragment(event.id))
        }
    }
}