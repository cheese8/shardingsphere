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

package org.apache.shardingsphere.sql.parser.statement.core.statement.dml;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.sql.parser.statement.core.segment.dml.column.ColumnSegment;
import org.apache.shardingsphere.sql.parser.statement.core.segment.dml.prepare.PrepareStatementQuerySegment;
import org.apache.shardingsphere.sql.parser.statement.core.segment.generic.table.SimpleTableSegment;
import org.apache.shardingsphere.sql.parser.statement.core.statement.AbstractSQLStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.available.TableAvailable;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Copy statement.
 */
@RequiredArgsConstructor
@Getter
public final class CopyStatement extends AbstractSQLStatement implements DMLStatement, TableAvailable {
    
    private final SimpleTableSegment table;
    
    private final Collection<ColumnSegment> columns;
    
    private final PrepareStatementQuerySegment prepareStatementQuery;
    
    /**
     * Get table.
     *
     * @return table
     */
    public Optional<SimpleTableSegment> getTable() {
        return Optional.ofNullable(table);
    }
    
    /**
     * Get prepare statement query segment.
     *
     * @return prepare statement query segment
     */
    public Optional<PrepareStatementQuerySegment> getPrepareStatementQuery() {
        return Optional.ofNullable(prepareStatementQuery);
    }
    
    @Override
    public Collection<SimpleTableSegment> getTables() {
        return null == table ? Collections.emptyList() : Collections.singletonList(table);
    }
}
