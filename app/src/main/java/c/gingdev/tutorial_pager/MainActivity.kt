package c.gingdev.tutorial_pager

import android.animation.ArgbEvaluator
import android.graphics.Color
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

import c.gingdev.tutorial_pager.Fragments.FirstTutorialPage
import c.gingdev.tutorial_pager.Fragments.SecondTutorialPage
import c.gingdev.tutorial_pager.Fragments.ThirdTutorialPage

import com.rd.animation.type.AnimationType


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
		ViewPager.OnPageChangeListener,
		View.OnClickListener{

	val firstPageColor = Color.CYAN
	val secondPageColor = Color.MAGENTA
	val thirdPageColor = Color.YELLOW

	val colorList : Array<Int> = arrayOf(firstPageColor, secondPageColor, thirdPageColor)

	val evaluator = ArgbEvaluator()
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		init()
	}

	private fun init() {
		ViewPager.adapter = PagerAdapter(supportFragmentManager)
		ViewPager.addOnPageChangeListener(this)

		indicator.setViewPager(ViewPager)
		indicator.setAnimationType(AnimationType.WORM)
		indicator.count = colorList.size
		indicator.selection = 0

		tutorialNextBtn.setOnClickListener(this)
		tutorialSkipBtn.setOnClickListener(this)
	}

//	OnClick

	override fun onClick(v: View?) {
		when(v) {
			tutorialNextBtn -> {
				ViewPager.setCurrentItem(ViewPager.currentItem + 1, true)
			}
			tutorialSkipBtn -> {
				ViewPager.setCurrentItem(colorList.size, true)
			}
		}
	}

// ViewPager Listener

	override fun onPageScrollStateChanged(position: Int) {}

	override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
		var colorUpdate: Int = evaluator.evaluate(positionOffset, colorList[position], colorList[if (position == 2) position else position + 1]) as Int
		ViewPager.setBackgroundColor(colorUpdate)
	}

	override fun onPageSelected(position: Int) {
		when (position) {
			0 -> ViewPager.setBackgroundColor(firstPageColor)
			1 -> ViewPager.setBackgroundColor(secondPageColor)
			2 -> ViewPager.setBackgroundColor(thirdPageColor)
		}
		if (position == 2) {
			tutorialNextBtn.text = "Finish"
			tutorialSkipBtn.visibility = View.GONE
		} else{
			tutorialNextBtn.text = "Next"
			tutorialSkipBtn.visibility = View.VISIBLE
		}
        indicator.selection = position
	}

    private class PagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

	    override fun getItem(position: Int): Fragment {
				return when (position) {
					0 -> FirstTutorialPage()
					1 -> SecondTutorialPage()
					2 -> ThirdTutorialPage()

					else -> FirstTutorialPage()
				}
	    }

	    override fun getCount(): Int {
		    return 3
	    }
    }
}
