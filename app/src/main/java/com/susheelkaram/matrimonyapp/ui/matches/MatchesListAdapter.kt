package com.susheelkaram.matrimonyapp.ui.matches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.susheelkaram.matrimonyapp.R
import com.susheelkaram.matrimonyapp.data.model.InvitationStatus
import com.susheelkaram.matrimonyapp.data.model.MatchUser
import com.susheelkaram.matrimonyapp.databinding.MatchItemBinding
import com.susheelkaram.matrimonyapp.util.hide
import com.susheelkaram.matrimonyapp.util.show

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
class MatchesListAdapter constructor(private val onInvitationAction: onInvitationAction) :
    RecyclerView.Adapter<MatchesListAdapter.MatchViewHolder>() {
    var matchesList = mutableListOf<MatchUser>()

    class MatchViewHolder(val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = DataBindingUtil.inflate<MatchItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.match_item,
            parent,
            false
        )
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val binding = holder.binding
        val item = matchesList[position]

        binding.textName.text = item.name
        binding.textQuickinfo.text = "${item.age}, ${item.location}"

        if (item.invitationStatus != InvitationStatus.NONE) {
            binding.btnReject.hide()
            binding.btnAccept.hide()
            binding.textInvitationStatus.show()
            binding.textInvitationStatus.text =
                if (item.invitationStatus == InvitationStatus.REJECTED) "Invitation Rejected" else "Invitation Accepted"
        } else {
            binding.btnReject.setOnClickListener {
                onInvitationAction.onInvitationActionClick(item, InvitationStatus.REJECTED)
            }
            binding.btnAccept.setOnClickListener {
                onInvitationAction.onInvitationActionClick(item, InvitationStatus.ACCEPTED)
            }
        }
        if (item.profileImageUrl.isNotEmpty()) {
            Glide.with(binding.root.context)
                .load(item.profileImageUrl)
                .placeholder(R.drawable.ic_baseline_person_24)
                .fallback(R.drawable.ic_baseline_person_24)
                .circleCrop()
                .into(binding.imgProfile)
        }
    }

    override fun getItemCount(): Int {
        return matchesList.size
    }

    fun setData(list: List<MatchUser>) {
        matchesList.clear()
        matchesList.addAll(list)
        notifyDataSetChanged()
    }

}

interface onInvitationAction {
    fun onInvitationActionClick(user: MatchUser, invitationStatus: Int)
}