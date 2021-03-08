package com.zq.base.preference

/**
 *
 */
class BasicDataPreferenceUtil private constructor() : BasePreferences() {
    protected override val sPFileName = "network_api_module_basic_data_preference"

    companion object {
        @JvmStatic
        fun getInstance() = Holder.holder
    }

    class Holder() {
        companion object {
            @JvmStatic
            val holder = BasicDataPreferenceUtil()
        }
    }
}