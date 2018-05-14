
import com.example.sravanreddy.realestateproject.base.BasePresenter
import com.example.sravanreddy.realestateproject.base.BaseView
import com.example.sravanreddy.realestateproject.models.propertybean.PropertyRes
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.PolygonOptions
import java.util.*

interface BoundaryContract {

    interface IView : BaseView<IPresenter> {
        fun showProgressDlg()

        fun showArea(polygonOption1: PolygonOptions, citiesFound: ArrayList<LatLng>)
        fun showPropertyOnMap(propertyRes: PropertyRes)
        fun showDetailDialog(it: Marker?, propertyRes: PropertyRes)
    }

    interface IPresenter : BasePresenter {
        fun setMapReady(p0: GoogleMap, cityName: String)
        fun markClick(it: Marker?, propertyRes: PropertyRes): Boolean
    }
}