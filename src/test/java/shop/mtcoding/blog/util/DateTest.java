package shop.mtcoding.blog.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Test;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;
import java.util.Date;

public class DateTest {
    @Test
    public void timestampFormat(){
        //given
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        //when
        String createdAt = MyDateUtil.timestampFormat(currentTimestamp);
        //then
        System.out.println("timestampFormat_test: " + createdAt);
    }
    @Test
    public void format_test(){
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Date currentDate = new Date(currentTimestamp.getTime());
        String formattedDate = DateFormatUtils.format(currentDate,"yyyy-MM-dd HH:mm");
        System.out.println("Formatted Date: "+formattedDate);
    }
}
