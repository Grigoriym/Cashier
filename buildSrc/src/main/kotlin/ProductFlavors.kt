interface ProductFlavor{

    companion object{
        const val DEV = "dev"
        const val QA = "qa"
        const val PROD = "prod"
    }

}

object ProductFlavorDev:ProductFlavor{

}