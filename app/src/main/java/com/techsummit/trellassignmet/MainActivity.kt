package com.techsummit.trellassignmet

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.techsummit.trellassignmet.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

	companion object {
		private const val TAG = "MainActivity"
	}

	private val videos = mutableListOf<Video>()
	private lateinit var binding : ActivityMainBinding
	private lateinit var videosViewModel: VideosViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		requestPermissions()
		initViews()

		videosViewModel =
			ViewModelProviders.of(this, VideosViewModelFactory()).get(VideosViewModel::class.java)
	}

	private fun initViews() {
		videosRv.layoutManager = LinearLayoutManager(this)
		PagerSnapHelper().attachToRecyclerView(videosRv)
		videosRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
			override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
				super.onScrolled(recyclerView, dx, dy)
				val lastVisiblePosition = (videosRv.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
				videos.forEachIndexed { index, video ->
					kotlin.run {
						//video.player?.playWhenReady = index == lastVisiblePosition
					}
				}
			}
		})
	}

	private fun requestPermissions() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !== PackageManager.PERMISSION_GRANTED) {
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
				ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
			} else {
				ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
			}
		} else {
			//goToVideos()
		}
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
		when (requestCode) {
			1 -> {
				if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) === PackageManager.PERMISSION_GRANTED)) {
						//goToVideos()
					}
				} else {
					Toast.makeText(this, "Cannot go further without granting permission", Toast.LENGTH_LONG).show()
				}
				return
			}
		}
	}
}