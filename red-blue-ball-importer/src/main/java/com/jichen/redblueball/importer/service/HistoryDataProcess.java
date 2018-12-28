package com.jichen.redblueball.importer.service;

import com.jichen.redblueball.model.History;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.jichen.redblueball.model.common.BallFactory.createBlueBall;
import static com.jichen.redblueball.model.common.BallFactory.createRedBall;


@Component
public class HistoryDataProcess {

    private static final String NUMBER_REGEX = "\\d{7}";
    private static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    private static final String RED_BALLS_REGEX = "\\d{2},\\d{2},\\d{2},\\d{2},\\d{2},\\d{2}";
    private static final String RED_BALLS_SPLITTER = ",";
    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryDataProcess.class);

    public List<History> process(List<String> dataList) {
        return dataList.stream().map(this::translateToHistory).collect(Collectors.toList());
    }

    private History translateToHistory(String data) {
        History history = new History();
        history.setNumber(getNumber(data));
        String redBalls = getRegexString(data, RED_BALLS_REGEX);
        String[] redBallArray = redBalls.split(RED_BALLS_SPLITTER);
        history.setRed1(createRedBall(Integer.parseInt(redBallArray[0])));
        history.setRed2(createRedBall(Integer.parseInt(redBallArray[1])));
        history.setRed3(createRedBall(Integer.parseInt(redBallArray[2])));
        history.setRed4(createRedBall(Integer.parseInt(redBallArray[3])));
        history.setRed5(createRedBall(Integer.parseInt(redBallArray[4])));
        history.setRed6(createRedBall(Integer.parseInt(redBallArray[5])));
        history.setBlueBall(createBlueBall(getBlueBallNumber(data)));
        try {
            history.setDate(getDate(data));
        } catch (Exception e) {
            history.setDate(null);
            LOGGER.warn("Date parse exception", e.getMessage(), data);
        }
        return history;
    }

    private int getBlueBallNumber(String data) {
        int index = data.indexOf("\\");
        return Integer.parseInt(data.substring(index + 1, data.length()));
    }

    private int getNumber(String data) {
        String numberStr = getRegexString(data, NUMBER_REGEX);
        return Integer.parseInt(numberStr);
    }

    private Date getDate(String data) throws ParseException {
        String date = getRegexString(data, DATE_REGEX);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    private String getRegexString(String data, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        String number = null;
        if (matcher.find()) {
            number = matcher.group();
        }
        return number;
    }

}
