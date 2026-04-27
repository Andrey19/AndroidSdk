package ru.effectivemobile.androidsdk.task4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.effectivemobile.androidsdk.R
import java.text.SimpleDateFormat
import java.util.*

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {

    private var messages = listOf<ChatMessage>()

    fun submitList(newMessages: List<ChatMessage>) {
        messages = newMessages
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = messages.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        private val tvTimestamp: TextView = itemView.findViewById(R.id.tvTimestamp)

        fun bind(message: ChatMessage) {
            tvMessage.text = message.text

            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            tvTimestamp.text = dateFormat.format(Date(message.timestamp))

            val layoutParams = tvMessage.layoutParams as ViewGroup.MarginLayoutParams
            if (message.isMine) {
                tvMessage.setBackgroundResource(R.drawable.bg_my_message)
                layoutParams.marginStart = 100
                layoutParams.marginEnd = 16
            } else {
                tvMessage.setBackgroundResource(R.drawable.bg_other_message)
                layoutParams.marginStart = 16
                layoutParams.marginEnd = 100
            }
            tvMessage.layoutParams = layoutParams
        }
    }
}