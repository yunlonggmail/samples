/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yunlong.qrcode.result;


import android.app.Activity;

import com.yunlong.qrcode.handling.Result;
import com.yunlong.qrcode.handling.client.result.ParsedResult;
import com.yunlong.qrcode.handling.client.result.ResultParser;

/**
 * Manufactures Android-specific handlers based on the barcode content's type.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class ResultHandlerFactory {
    private ResultHandlerFactory() {
    }

    public static ResultHandler makeResultHandler(Activity activity, Result rawResult) {
        ParsedResult result = parseResult(rawResult);
        switch (result.getType()) {
            case ADDRESSBOOK:
                return new AddressBookResultHandler(activity, result);
            case EMAIL_ADDRESS:
                return new EmailAddressResultHandler(activity, result);
            case URI:
                return new URIResultHandler(activity, result);
            case GEO:
                return new GeoResultHandler(activity, result);
            case TEL:
                return new TelResultHandler(activity, result);
            case SMS:
                return new SMSResultHandler(activity, result);
            case CALENDAR:
                return new CalendarResultHandler(activity, result);
            default:
                return new TextResultHandler(activity, result, rawResult);
        }
    }

    private static ParsedResult parseResult(Result rawResult) {
        return ResultParser.parseResult(rawResult);
    }
}
