package com.francisco.sid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.francisco.sid.R
import com.francisco.sid.databinding.FragmentCheckinFormBinding
import com.francisco.sid.util.DialogUtils
import com.francisco.sid.util.setTextWatcherDeErro
import com.francisco.sid.viewmodel.CheckinViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CheckinFormFragment: DaggerFragment() {

    private lateinit var binding: FragmentCheckinFormBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CheckinViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(CheckinViewModel::class.java)
    }

    private val eventId: String by lazy {
        arguments?.let {
            CheckinFormFragmentArgs.fromBundle(it).eventId
        } ?: throw ExceptionInInitializerError()
    }

    private val progressDialog by lazy {
        DialogUtils()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckinFormBinding.inflate(layoutInflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configInputWatcher()

        viewModel.shouldSubmitLiveEvent.observe(viewLifecycleOwner, {
            if (it) {
                viewModel.checkin(eventId)
            }
        })

        viewModel.checkin.observe(viewLifecycleOwner, { state ->
            state.handleIt(
                loading = {
                    progressDialog.showProgressDialog(requireActivity(), false)
                },
                success = {
                  Navigation.findNavController(requireView()).popBackStack()
                },
                exception = {
                    Snackbar.make(
                        requireView(),
                        getString(R.string.checkin_error),
                        Snackbar.LENGTH_LONG
                    ).show()
                },
                stopLoading = {
                    progressDialog.hideProgressDialog()
                }
            )
        })

    }

    private fun configInputWatcher() {
        binding.nameEditText.setTextWatcherDeErro(viewModel.nameErrorLiveEvent)
        binding.emailInputEditText.setTextWatcherDeErro(viewModel.emailErrorLiveEvent)
    }
}