package chihane.jdaddressselector;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.litepal.crud.DataSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import chihane.jdaddressselector.model.AddressItem;

/**
 * Created by zz on 2018/1/25.
 */

public class AddressUtils {

    /**
     * @param context
     * @param assetsFileName
     * 如果表AddressItem不纯在，则将json文件转换成数据库文件
     * 若纯在，则不做重新创建
     */
    public static void CreateDBData(Context context, String assetsFileName) {

        if (!DataSupport.isExist(AddressItem.class)) {
            StringBuilder jsonSB = new StringBuilder();
            try {
                BufferedReader addressJsonStream = new BufferedReader(new InputStreamReader(context.getAssets().open(assetsFileName)));
                String line;
                while ((line = addressJsonStream.readLine()) != null) {
                    jsonSB.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 将数据转换为对象
            List<AddressItem> list = new Gson().fromJson(jsonSB.toString(), new TypeToken<List<AddressItem>>() {
            }.getType());
            DataSupport.saveAll(list);
        }
    }
}
