import com.example.sravanreddy.realestateproject.base.BasePresenter
import com.example.sravanreddy.realestateproject.base.BaseView
import com.google.android.gms.maps.GoogleMap

interface BoundaryContract {

    interface IView : BaseView<IPresenter> {
    }

    interface IPresenter : BasePresenter {
        fun setMapReady(p0: GoogleMap, cityName: String)
    }
}