package chihane.jdaddressselector;

import android.content.Context;

import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;
import chihane.jdaddressselector.model.AddressItem;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

public class DefaultAddressProvider implements AddressProvider {
    public DefaultAddressProvider(Context context) {
    }

    @Override
    public void provideProvinces(AddressReceiver<Province> addressReceiver) {
        List<AddressItem> list= DataSupport
                .where("parentId= ?","0")
                .find(AddressItem.class);

        List<Province> provinceList = new ArrayList<>();
        for (AddressItem item :list){
            Province  province = new Province();
            province.id=Integer.parseInt(item.getAreaCode());
            province.name =item.getAreaName();
            provinceList.add(province);
        }

        addressReceiver.send(provinceList);
    }

    @Override
    public void provideCitiesWith(int provinceId, AddressReceiver<City> addressReceiver) {

        List<AddressItem>  list=DataSupport
                .where("parentId= ?",provinceId+"")
                .find(AddressItem.class);

        List<City> CityList = new ArrayList<>();
        for (AddressItem item :list){
            City city = new City();
            city.province_id = provinceId;
            city.id=Integer.parseInt(item.getAreaCode());
            city.name =item.getAreaName();
            CityList.add(city);
        }

        addressReceiver.send(CityList);
    }

    @Override
    public void provideCountiesWith(int cityId, AddressReceiver<County> addressReceiver) {


        List<AddressItem>  list=DataSupport
                .where("parentId= ?",cityId+"")
                .find(AddressItem.class);

        List<County> CountyList = new ArrayList<>();
        for (AddressItem item :list){
            County county = new County();
            county.city_id = cityId;
            county.id=Integer.parseInt(item.getAreaCode());
            county.name =item.getAreaName();
            CountyList.add(county);
        }

        addressReceiver.send(CountyList);
    }

    @Override
    public void provideStreetsWith(int countyId, AddressReceiver<Street> addressReceiver) {


        List<AddressItem>  list=DataSupport
                .where("parentId= ?",countyId+"")
                .find(AddressItem.class);

        List<Street> StreetList = new ArrayList<>();
        for (AddressItem item :list){
            Street street = new Street();
            street.county_id = countyId;
            street.id=Integer.parseInt(item.getAreaCode());
            street.name =item.getAreaName();
            StreetList.add(street);
        }

        addressReceiver.send(StreetList);
    }
   /* public DefaultAddressProvider(Context context) {
        FlowManager.init(new FlowConfig.Builder(context.getApplicationContext()).build());
    }

    @Override
    public void provideProvinces(final AddressReceiver<Province> addressReceiver) {
        final FlowQueryList<Province> provinceQueryList = SQLite.select()
                .from(Province.class)
                .flowQueryList();
        addressReceiver.send(new ArrayList<>(provinceQueryList));
    }

    @Override
    public void provideCitiesWith(int provinceId, final AddressReceiver<City> addressReceiver) {
        final FlowQueryList<City> cityQueryList = SQLite.select()
                .from(City.class)
                .where(City_Table.province_id.eq(provinceId))
                .flowQueryList();
        addressReceiver.send(new ArrayList<>(cityQueryList));
    }

    @Override
    public void provideCountiesWith(int cityId, final AddressReceiver<County> addressReceiver) {
        final FlowQueryList<County> countyQueryList = SQLite.select()
                .from(County.class)
                .where(County_Table.city_id.eq(cityId))
                .flowQueryList();
        addressReceiver.send(new ArrayList<>(countyQueryList));
    }

    @Override
    public void provideStreetsWith(int countyId, final AddressReceiver<Street> addressReceiver) {
        final FlowQueryList<Street> streetQueryList = SQLite.select()
                .from(Street.class)
                .where(Street_Table.county_id.eq(countyId))
                .flowQueryList();
        addressReceiver.send(new ArrayList<>(streetQueryList));
    }*/
}
