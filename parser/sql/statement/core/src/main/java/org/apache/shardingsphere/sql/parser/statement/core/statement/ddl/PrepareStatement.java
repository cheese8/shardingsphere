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

package org.apache.shardingsphere.sql.parser.statement.core.statement.ddl;

import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.sql.parser.statement.core.extractor.TableExtractor;
import org.apache.shardingsphere.sql.parser.statement.core.segment.generic.table.SimpleTableSegment;
import org.apache.shardingsphere.sql.parser.statement.core.statement.AbstractSQLStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.available.TableAvailable;
import org.apache.shardingsphere.sql.parser.statement.core.statement.dml.DeleteStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.dml.InsertStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.dml.SelectStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.dml.UpdateStatement;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Prepare statement.
 */
@Getter
@Setter
public final class PrepareStatement extends AbstractSQLStatement implements DDLStatement, TableAvailable {
    
    private SelectStatement select;
    
    private InsertStatement insert;
    
    private UpdateStatement update;
    
    private DeleteStatement delete;
    
    /**
     * Get select statement.
     *
     * @return select statement
     */
    public Optional<SelectStatement> getSelect() {
        return Optional.ofNullable(select);
    }
    
    /**
     * Get insert statement.
     *
     * @return insert statement
     */
    public Optional<InsertStatement> getInsert() {
        return Optional.ofNullable(insert);
    }
    
    /**
     * Get update statement.
     *
     * @return update statement
     */
    public Optional<UpdateStatement> getUpdate() {
        return Optional.ofNullable(update);
    }
    
    /**
     * Get delete statement.
     *
     * @return delete statement
     */
    public Optional<DeleteStatement> getDelete() {
        return Optional.ofNullable(delete);
    }
    
    @Override
    public Collection<SimpleTableSegment> getTables() {
        TableExtractor tableExtractor = new TableExtractor();
        Optional.ofNullable(select).ifPresent(tableExtractor::extractTablesFromSelect);
        Optional.ofNullable(insert).ifPresent(tableExtractor::extractTablesFromInsert);
        Optional.ofNullable(update).ifPresent(tableExtractor::extractTablesFromUpdate);
        Optional.ofNullable(delete).ifPresent(tableExtractor::extractTablesFromDelete);
        return new LinkedList<>(tableExtractor.getRewriteTables());
    }
}
