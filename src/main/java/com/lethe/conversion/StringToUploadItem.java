package com.lethe.conversion;

import com.lethe.form.UniformBackingObjects;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by ghadahalghamdi on 02/07/2016.
 */
public class StringToUploadItem implements Converter<String, UniformBackingObjects> {

    @Override
    public UniformBackingObjects convert(String source) {

      //  UniformBackingObjects uploadItem = new UniformBackingObjects();
      //  return uploadItem.uploadItem(source);
        return null;

    }

}

/*public class StringToUploadItem extends PropertyEditorSupport {

    public void setAsText(String text) {
        UniformBackingObjects uploadItem = new UniformBackingObjects();
        setValue(uploadItem.uploadItem(text));
    }
}*/

