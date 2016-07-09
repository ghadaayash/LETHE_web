package com.lethe.conversion;

import com.lethe.form.UploadItem;
import org.semanticweb.owlapi.model.OWLEntity;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import java.beans.PropertyEditorSupport;

/**
 * Created by ghadahalghamdi on 02/07/2016.
 */
public class StringToUploadItem implements Converter<String, UploadItem> {

    @Override
    public UploadItem convert(String source) {

      //  UploadItem uploadItem = new UploadItem();
      //  return uploadItem.uploadItem(source);
        return null;

    }

}

/*public class StringToUploadItem extends PropertyEditorSupport {

    public void setAsText(String text) {
        UploadItem uploadItem = new UploadItem();
        setValue(uploadItem.uploadItem(text));
    }
}*/

