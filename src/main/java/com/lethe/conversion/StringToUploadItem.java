package com.lethe.conversion;

import com.lethe.form.FormBackingObjects;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by ghadahalghamdi on 02/07/2016.
 */
public class StringToUploadItem implements Converter<String, FormBackingObjects> {

    @Override
    public FormBackingObjects convert(String source) {

      //  FormBackingObjects uploadItem = new FormBackingObjects();
      //  return uploadItem.uploadItem(source);
        return null;

    }

}

/*public class StringToUploadItem extends PropertyEditorSupport {

    public void setAsText(String text) {
        FormBackingObjects uploadItem = new FormBackingObjects();
        setValue(uploadItem.uploadItem(text));
    }
}*/

