package c.gingdev.tutorial_pager.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import c.gingdev.tutorial_pager.R
import kotlinx.android.synthetic.main.tutorial_layout.view.*

class FirstTutorialPage: Fragment() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val layout = inflater.inflate(R.layout.tutorial_layout, container, false)
		layout.TextView.text = "1 번째 텍스트뷰"

		return layout
	}
}