package me.rosuh.filepicker.config

import android.app.Activity
import android.support.v4.app.Fragment
import java.lang.ref.WeakReference

/**
 *
 * @author rosu
 * @date 2018/11/22
 */
object FilePickerManager{


    /**
     * 启动 Launcher Activity 所需的 request code
     */
    const val REQUEST_CODE = 10401
    /**
     * 获取 context 返回结果时，所需的 intent.extra key
     */
    val RESULT_KEY = "FILE_PICKER_MANAGER"

    internal var context:WeakReference<Activity> ?= null
    internal var fragment:WeakReference<Fragment> ?= null
    internal lateinit var config:FilePickerConfig

    fun from(activity: Activity):FilePickerConfig{
        this.context = WeakReference(activity)
        this.config = FilePickerConfig(this)
        return this.config
    }

    /**
     * 不能使用 fragment.getContext()，因为无法保证外部的代码环境
     */
    fun from(fragment: Fragment):FilePickerConfig{
        this.fragment = WeakReference(fragment)
        this.context = WeakReference(fragment.activity!!)
        this.config = FilePickerConfig(this)
        return this.config
    }

    private var dataList: List<String> = ArrayList()

    /**
     * 保存数据@param list List<String>到本类中
     */
    fun saveData(list: List<String>) {
        dataList = list
    }

    /**
     * 供调用者获取结果
     * @return List<String>
     */
    fun obtainData(): List<String> {
        return dataList
    }
}