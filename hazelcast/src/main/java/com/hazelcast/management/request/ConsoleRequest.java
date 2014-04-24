/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.management.request;

import com.eclipsesource.json.JsonObject;
import com.hazelcast.management.JsonSerializable;
import com.hazelcast.management.ManagementCenterService;
import java.io.IOException;

public interface ConsoleRequest extends JsonSerializable {

    int getType();

    Object readResponse(JsonObject in) throws IOException;

    void writeResponse(ManagementCenterService mcs, JsonObject out) throws Exception;

//    public void setFieldsFromJson(JsonObject fields)
//    {
//        Class<?> c = this.getClass();
//        try {
//            final Iterator<JsonObject.Member> iterator = fields.iterator();
//            while (iterator.hasNext()) {
//                final JsonObject.Member element = iterator.next();
//                final Field field = c.getDeclaredField(element.getName());
//                field.setAccessible(true);
//                final JsonObject value = element.getValue().asObject();
//                final String type = value.get("type").asString();
//                Method method = value.asObject().getClass().getMethod("as" + type);
//                field.set(this, method.invoke(value.get("value")));
//            }
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }

}
