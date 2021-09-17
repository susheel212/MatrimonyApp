package com.susheelkaram.matrimonyapp.ui.matches

import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.susheelkaram.matrimonyapp.MatrimonyApp
import com.susheelkaram.matrimonyapp.R
import com.susheelkaram.matrimonyapp.data.MatchesRepository
import com.susheelkaram.matrimonyapp.data.model.MatchUser
import com.susheelkaram.matrimonyapp.databinding.ActivityMatchesBinding
import com.susheelkaram.matrimonyapp.ui.UiEvent
import com.susheelkaram.matrimonyapp.ui.UiState
import com.susheelkaram.matrimonyapp.util.*
import javax.inject.Inject

class MatchesActivity : AppCompatActivity() {
    lateinit var binding: ActivityMatchesBinding
    lateinit var vm: MatchesViewModel

    @Inject
    lateinit var matchesRepository: MatchesRepository
    lateinit var matchListAdapter: MatchesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)
        MatrimonyApp.getComponent().inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_matches)
        vm = ViewModelProvider(
            this,
            MatchesViewModelFactory(matchesRepository)
        ).get(MatchesViewModel::class.java)

        setupRecyclerView()
        initialize()
    }

    fun initialize() {
        vm.getStateData().observe(this) {
            when (it) {
                is UiState.Loading -> {
                    binding.progressCircular.show()
                    binding.rvMatchlist.hide()
                }
                is UiState.Success<*> -> {
                    binding.progressCircular.hide()
                    binding.rvMatchlist.show()
                    matchListAdapter.setData(it.data as List<MatchUser>)
                }
                is UiState.Error -> it.error.message?.let {
                    binding.root.showSnackBar(it)
                }
            }
        }

        vm.getUiEventData().observe(this) {
            when (it) {
                is UiEvent.Message -> binding.root.showSnackBar(it.message)
            }
        }
        vm.fetchMatchesList(ConnectivityUtils.isInternetAvailable(this))
    }

    fun setupRecyclerView() {
        matchListAdapter = MatchesListAdapter(object : onInvitationAction {
            override fun onInvitationActionClick(user: MatchUser, invitationStatus: Int) {
                vm.updateInvitationStatus(user, invitationStatus)
            }
        })
        binding.rvMatchlist.layoutManager = LinearLayoutManager(this)
        binding.rvMatchlist.adapter = matchListAdapter
    }
}