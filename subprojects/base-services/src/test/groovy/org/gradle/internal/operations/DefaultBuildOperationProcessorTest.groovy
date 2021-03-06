/*
 * Copyright 2015 the original author or authors.
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

package org.gradle.internal.operations

import spock.lang.Specification
import spock.lang.Unroll

class DefaultBuildOperationProcessorTest extends Specification {

    @Unroll
    def "all #operations operations run to completion when using #maxThreads threads"() {
        given:
        BuildOperationProcessor buildOperationProcessor = new DefaultBuildOperationProcessor(maxThreads)
        def operation = Mock(Runnable)
        def worker = new DefaultOperationQueueTest.SimpleWorker()

        when:
        def queue = buildOperationProcessor.newQueue(worker)
        operations.times { queue.add(operation) }
        and:
        queue.waitForCompletion()

        then:
        operations * operation.run()

        where:
        operations | maxThreads
        1          | -1
        1          | 0
        1          | 1
        1          | 4
        5          | -1
        5          | 0
        5          | 1
        5          | 4
        20         | -1
        20         | 0
        20         | 1
        20         | 4
    }
}
