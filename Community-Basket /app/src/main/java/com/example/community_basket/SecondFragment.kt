package com.example.community_basket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.community_basket.databinding.FragmentSecondBinding


class SecondFragment : Fragment(R.layout.fragment_second) {
    private var CHANNEL_ID = "channel"
    private lateinit var dataBinding: FragmentSecondBinding

    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_second,
            container,
            false
        )
        return dataBinding.root
    }


    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            val title = bundle.getString(FirstFragment.MOVIE_TITLE)
            dataBinding.movieTitleFragment.text = title
//            (view.findViewById(R.id.movie_title_fragment) as TextView).text = title
            val movie: ProductModel? = bundle.getParcelable(FirstFragment.MOVIE)
            if (movie != null) {
                dataBinding.movieTitleFragment.text = movie.product_name
                dataBinding.tvDuration.text = movie.product_location
                dataBinding.imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        dataBinding.imageView.context,
                        movie.imageId
                    )
                )
            }
            createNotif()
        }
    }

    private fun createNotif() {
        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Notificare")
            .setContentText("Descriere notificare")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager =
                requireActivity().getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        val notificationManager = NotificationManagerCompat.from(requireContext())
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build())
    }
}