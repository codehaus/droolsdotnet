package org.drools.audit.event;

/*
 * Copyright 2005 JBoss Inc
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

/**
 * An event filter that can be used to filter log events.
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen </a>
 */
public interface ILogEventFilter {

    /**
     * Returns whether the given event should be filtered from the event log or not.
     * @param event The log event
     * @return Whether the event should be filtered from the event log or not.
     */
    boolean acceptEvent(LogEvent event);

}
