/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tika.metadata.filter;

import org.apache.tika.config.Field;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IncludeFieldMetadataFilter implements MetadataFilter {
    private final Set<String> includeSet;

    public IncludeFieldMetadataFilter() {
        this(new HashSet<>());
    }

    public IncludeFieldMetadataFilter(Set<String> fields) {
        this.includeSet = fields;
    }

    /**
     *
     * @param includeString comma-delimited list of fields to include
     */
    @Field
    public void setInclude(String includeString) {
        for (String include : includeString.split(",")) {
            includeSet.add(include);
        }
    }

    @Override
    public void filter(Metadata metadata) throws TikaException {

        for (String n : metadata.names()) {
            if (! includeSet.contains(n)) {
                metadata.remove(n);
            }
        }
    }
}