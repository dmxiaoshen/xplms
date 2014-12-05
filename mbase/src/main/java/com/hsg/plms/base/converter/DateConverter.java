
package com.hsg.plms.base.converter;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date>{
    
    private static final String[] PATTERNS = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "HH:mm:ss"};

    @Override
    public Date convert(String source) {
        if(StringUtils.isNotBlank(source)){
            try {
                return DateUtils.parseDateStrictly(source, PATTERNS);
            } catch (ParseException e) {              
            }
        }
        return null;
    }

}
