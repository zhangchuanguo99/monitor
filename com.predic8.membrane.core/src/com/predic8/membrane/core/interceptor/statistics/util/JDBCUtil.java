/* Copyright 2009 predic8 GmbH, www.predic8.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */
package com.predic8.membrane.core.interceptor.statistics.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.exchange.ExchangesUtil;
import static com.predic8.membrane.core.interceptor.statistics.util.DBTableConstants.*;

public class JDBCUtil {

	public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + DBTableConstants.TABLE_NAME + " ( id INT GENERATED ALWAYS AS IDENTITY, " +

	STATUS_CODE + " INT, " +

	TIME + " VARCHAR(155), " +

	RULE + " VARCHAR(255), " +

	METHOD + " VARCHAR(50), " +

	PATH + " VARCHAR(1000), " +

	CLIENT + " VARCHAR(255), " +

	SERVER + " VARCHAR(255), " +

	REQUEST_CONTENT_TYPE + " VARCHAR(100), " +

	REQUEST_CONTENT_LENGTH + " INT, " +

	RESPONSE_CONTENT_TYPE + " VARCHAR(100), " +

	RESPONSE_CONTENT_LENGTH + " INT, " +

	DURATION + " INT " +

	")";


	public static String getPreparedInsert() {
		return "INSERT INTO " + TABLE_NAME + " (" +

		STATUS_CODE + "," +

		TIME + "," +

		RULE + "," +

		METHOD + "," +

		PATH + "," +

		CLIENT + "," +

		SERVER + "," +

		REQUEST_CONTENT_TYPE + "," +

		REQUEST_CONTENT_LENGTH + "," +

		RESPONSE_CONTENT_TYPE + "," +

		RESPONSE_CONTENT_LENGTH + "," +

		DURATION +

		")" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	}

	public static void readData(Exchange exc, PreparedStatement prepSt) throws SQLException {
		prepSt.setInt(1, exc.getResponse().getStatusCode());
		prepSt.setString(2, ExchangesUtil.getTime(exc));
		prepSt.setString(3, exc.getRule().toString());
		prepSt.setString(4, exc.getRequest().getMethod());
		prepSt.setString(5, exc.getRequest().getUri());
		prepSt.setString(6, exc.getSourceHostname());
		prepSt.setString(7, exc.getServer());
		prepSt.setString(8, exc.getRequestContentType());
		prepSt.setInt(9, exc.getRequestContentLength());
		prepSt.setString(10, exc.getResponseContentType());
		prepSt.setInt(11, exc.getResponseContentLength());
		prepSt.setLong(12, (exc.getTimeResReceived() - exc.getTimeReqSent()));
	}
}
