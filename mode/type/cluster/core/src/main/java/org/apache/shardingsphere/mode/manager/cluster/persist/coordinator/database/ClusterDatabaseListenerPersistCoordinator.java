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

package org.apache.shardingsphere.mode.manager.cluster.persist.coordinator.database;

import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.mode.node.path.engine.generator.NodePathGenerator;
import org.apache.shardingsphere.mode.node.path.type.global.state.DatabaseListenerCoordinatorNodePath;
import org.apache.shardingsphere.mode.spi.repository.PersistRepository;

/**
 * Cluster database listener persist coordinator.
 */
@RequiredArgsConstructor
public final class ClusterDatabaseListenerPersistCoordinator {
    
    private final PersistRepository repository;
    
    /**
     * Persist database listener assisted state.
     *
     * @param databaseName database name
     * @param clusterDatabaseListenerCoordinatorType database changed listener assisted type
     */
    public void persist(final String databaseName, final ClusterDatabaseListenerCoordinatorType clusterDatabaseListenerCoordinatorType) {
        repository.persistEphemeral(NodePathGenerator.toPath(new DatabaseListenerCoordinatorNodePath(databaseName)), clusterDatabaseListenerCoordinatorType.name());
    }
    
    /**
     * Delete database listener assisted state.
     *
     * @param databaseName database name
     */
    public void delete(final String databaseName) {
        repository.delete(NodePathGenerator.toPath(new DatabaseListenerCoordinatorNodePath(databaseName)));
    }
}
