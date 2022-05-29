package com.demo.shaadi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.shaadi.R
import com.demo.shaadi.data.local.entity.ShaadiUser
import com.demo.shaadi.data.model.ShaadiUsers
import com.demo.shaadi.databinding.ShaadiUserItemBinding
import com.demo.shaadi.utils.Constants

class ShaadiUsersAdapter(private var users: List<ShaadiUser>, val onAcceptDeclineListener: OnAcceptDeclineListener) :
    RecyclerView.Adapter<ShaadiUsersAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = ShaadiUserItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAdapterList(users: List<ShaadiUser>){
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        with(holder) {
            with(users[position]){
                binding.tvUserName.text = name
                binding.tvUserAge.text = "$age Y"
                binding.tvUserLocation.text = location
                Glide.with(holder.itemView.context)
                    .load(profileLarge)
                    .into(binding.imgUser)

                if(status != Constants.USER_REQUEST_PENDING){
                    binding.groupRequestBtn.visibility = View.GONE
                    binding.tvUserStatus.visibility = View.VISIBLE
                    binding.tvUserStatus.text = if(status == Constants.USER_ACCEPTED) holder.itemView.context.getString(R.string.member_accepted) else holder.itemView.context.getString(R.string.member_decline)
                }else{
                    binding.groupRequestBtn.visibility = View.VISIBLE
                    binding.tvUserStatus.visibility = View.GONE

                    var tempUser = this
                    binding.tvAccept.setOnClickListener {
                        tempUser.status = Constants.USER_ACCEPTED
                        onAcceptDeclineListener.onClick(tempUser)
                    }
                    tempUser.status = Constants.USER_DECLINED
                    binding.tvDecline.setOnClickListener {
                        onAcceptDeclineListener.onClick(tempUser)
                    }
                }
            }
        }
    }

    inner class RestaurantViewHolder(val binding: ShaadiUserItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnAcceptDeclineListener{
        fun onClick(shaadiUser: ShaadiUser)
    }

}