package br.com.albumdacopa.ui.activities

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import br.com.albumdacopa.R
import br.com.albumdacopa.database.database
import br.com.albumdacopa.eventbus.RxBus
import br.com.albumdacopa.eventbus.StickerListEvent
import br.com.albumdacopa.model.Sticker
import br.com.albumdacopa.ui.fragments.SettingsFragment
import br.com.albumdacopa.ui.fragments.StickerPagerFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find


open class DrawerActivity : AppCompatActivity() {

    // index to identify current nav menu item
    var navItemIndex = 0
    // tags used to attach the fragments
    private val TAG_STICKERS = "stickers"
    private val TAG_SETTINGS = "settings"
    var CURRENT_TAG = TAG_STICKERS

    private val mHandler: Handler = Handler()

    private val toolbar: Toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }
    private var navigationView: NavigationView? = null
    private val mDrawer: DrawerLayout by lazy {
        find<DrawerLayout>(R.id.drawer_layout)
    }

    private var mDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        loadNavHeader()
        setUpNavigationView()

        if (savedInstanceState == null) {
            navItemIndex = 0
            CURRENT_TAG = TAG_STICKERS
            loadHomeFragment()
        }
    }

    fun loadStickers() {
        doAsync {
            val allStickers = Sticker.list(database)
            val repeatedStickers = Sticker.repeated(database)
            RxBus.publish(StickerListEvent(allStickers, repeatedStickers))
        }
    }

    private fun setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView?.setNavigationItemSelectedListener { menuItem ->
            // This method will trigger on item Click of navigation menu
            //Check to see which item was being clicked and perform appropriate action
            when (menuItem.itemId) {
            //Replacing the main content with ContentFragment Which is our Inbox View;
                R.id.nav_stickers -> {
                    navItemIndex = 0
                    CURRENT_TAG = TAG_STICKERS
                }
                R.id.nav_settings -> {
                    navItemIndex = 1
                    CURRENT_TAG = TAG_SETTINGS
                }
                else -> navItemIndex = 0
            }
            menuItem.isChecked = true
            loadHomeFragment()
            true
        }

        val actionBarDrawerToggle = object : ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {}
        //Setting the actionbarToggle to drawer layout
        mDrawer.addDrawerListener(actionBarDrawerToggle)
        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState()
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private fun loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu()

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (supportFragmentManager.findFragmentByTag(CURRENT_TAG) != null) {
            mDrawer.closeDrawers()
            return
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        val mPendingRunnable = Runnable {
            // update the main content by replacing fragments
            val fragment = getHomeFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out)
            fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG)
            fragmentTransaction.commitAllowingStateLoss()
        }

        mHandler.postDelayed(mPendingRunnable, 250)

        //Closing drawer on item click
        mDrawer.closeDrawers()

        // refresh toolbar menu
        invalidateOptionsMenu()
    }

    private fun getHomeFragment(): Fragment {
        when (navItemIndex) {
            0 -> {
                return StickerPagerFragment()
            }
            1 -> {
                return SettingsFragment()
            }
            else -> return StickerPagerFragment()
        }
    }

    private fun selectNavMenu() {
        navigationView!!.menu.getItem(navItemIndex).isChecked = true
    }

    private fun loadNavHeader() {
        navigationView = find(R.id.nav_view)
        val navHeader = (navigationView as NavigationView).getHeaderView(0)
        val txtName = navHeader.find<TextView>(R.id.name)
        val txtWebsite = navHeader.find<TextView>(R.id.website)
        val imgNavHeaderBg = navHeader.find<ImageView>(R.id.img_header_bg)
        val imgProfile = navHeader.find<ImageView>(R.id.img_profile)
        // name, website
        txtName.setText("Nichollas Fonseca")
        txtWebsite.setText("www.google.com")

        // loading header background image
        Glide.with(this)
                .load(R.drawable.nav_menu_header_bg)
                .into(imgNavHeaderBg)

        // Loading profile image
        Glide.with(this)
                .load(R.drawable.nav_menu_header_pic)
                .thumbnail(0.5f)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(imgProfile)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mDrawerToggle?.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return mDrawerToggle!!.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }
}
