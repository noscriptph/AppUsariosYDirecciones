package com.example.useraddreessbdbootcamp.views.addresslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.useraddreessbdbootcamp.R
import com.example.useraddreessbdbootcamp.entities.Address

class AddressListAdapter(private val onItemClick: (Address) -> Unit) : ListAdapter<Address, AddressListAdapter.AddressViewHolder>(
    AddressComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder.create(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class AddressViewHolder(itemView: View, private val onItemClick: (Address) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val streetNameItemView: TextView = itemView.findViewById(R.id.txtStreet)
        private val cityNameItemView: TextView = itemView.findViewById(R.id.txtCity)

        fun bind(address: Address) {
            streetNameItemView.text = address.street
            cityNameItemView.text = address.city
            itemView.setOnClickListener {
                onItemClick(address)
            }
        }

        companion object {
            fun create(parent: ViewGroup, onItemClick: (Address) -> Unit): AddressViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.address_item, parent, false)
                return AddressViewHolder(view, onItemClick)
            }
        }
    }

    class AddressComparator : DiffUtil.ItemCallback<Address>() {
        override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
            return oldItem.idAdress == newItem.idAdress
        }
    }
}
