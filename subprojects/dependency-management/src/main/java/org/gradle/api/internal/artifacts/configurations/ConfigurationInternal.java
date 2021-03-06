/*
 * Copyright 2011 the original author or authors.
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
package org.gradle.api.internal.artifacts.configurations;

import org.gradle.api.artifacts.Configuration;

public interface ConfigurationInternal extends Configuration, DependencyMetaDataProvider {
    ResolutionStrategyInternal getResolutionStrategy();

    String getPath();

    void includedInResolveResult();

    /**
     * @return the path to the project this configuration is attached to (or
     *         null if the configuration is detached)
     */
    String getProjectPath();

}