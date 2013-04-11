package com.cbandi.pnr;

public class C {
	public static final String DEBUG_TAG = "com.cbandi.pnr";
	public static final String INTENT_PNR_DATA_KEY = "com.chethanbandi.PNR_DATA";
	public static final String INTENT_PNR_KEY = "com.chethanbandi.PNR";
	public static final String INTENT_SMS_LIST_KEY = "com.chethanbandi.SMS_LIST";
	
	public static final String CHAR_SET = "UTF-8";
	public static final String PNR_URL = "http://apps.sairahul.com/pnr/v1/";
	public static final String PNR_QUERY_METHOD = "GET";
	public static final int PNR_QUERY_CONN_TIMEOUT = 15000;
	public static final int PNR_QUERY_READ_TIMEOUT = 10000;
	
	public static final String STATUS_MESSAGE_GENERIC_ERROR = "An error occurred while getting the pnr status. Please try again";
	public static final String STATUS_MESSAGE_NO_CONNECTION = "No internet connection";
	
	public static final int STATUS_CODE_GENERIC_ERROR = 1;
	public static final int STATUS_CODE_NO_CONNECTION = 2;
	
	public static final String KEY_TRAIN_NUMBER = "TrainNumber";
	public static final String KEY_TRAIN_NAME = "TrainName";
	public static final String KEY_PNR = "PNR";
	public static final String KEY_FROM = "From";
	public static final String KEY_TO = "To";
	public static final String KEY_CLASS = "Class";
	public static final String KEY_RESERVED_UPTO = "ReservedUpto";
	public static final String KEY_BOARDING_POINT = "BoardingPoint";
	public static final String KEY_BOARDING_DATE = "BoardingDate";
	public static final String KEY_CHARTING_STATUS = "ChartingStatus";
	public static final String KEY_PASSENGER = "passenger";
	public static final String KEY_CURRENT_STATUS = "CurrentStatus";
	public static final String KEY_BOOKING_STATUS = "BookingStatus";
	public static final String KEY_PASSENGER_NAME = "Name";
	public static final String KEY_STATUS = "Status";
	public static final String KEY_STATUS_CODE = "code";
	public static final String KEY_STATUS_MESSAGE = "message";
	
	public static final String LABEL_TRAIN_NUMBER = "Train Number";
	public static final String LABEL_TRAIN_NAME = "Train Name";
	public static final String LABEL_PNR = "PNR";
	public static final String LABEL_FROM = "From";
	public static final String LABEL_TO = "To";
	public static final String LABEL_CLASS = "Class";
	public static final String LABEL_RESERVED_UPTO = "Reserved Upto";
	public static final String LABEL_BOARDING_POINT = "Boarding Point";
	public static final String LABEL_BOARDING_DATE = "Boarding Date";
	public static final String LABEL_CHARTING_STATUS = "Charting Status";
	public static final String LABEL_PASSENGER = "Passenger";
	public static final String LABEL_CURRENT_STATUS = "Current Status";
	public static final String LABEL_BOOKING_STATUS = "Booking Status";
	public static final String LABEL_PASSENGER_NAME = "Name";
	public static final String LABEL_STATUS = "Status";
	public static final String LABEL_STATUS_CODE = "Code";
	public static final String LABEL_STATUS_MESSAGE = "Message";
	
}

