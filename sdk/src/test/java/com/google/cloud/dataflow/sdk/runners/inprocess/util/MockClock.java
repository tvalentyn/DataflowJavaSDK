/*
 * Copyright (C) 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.cloud.dataflow.sdk.runners.inprocess.util;

import static com.google.common.base.Preconditions.checkArgument;

import org.joda.time.Instant;

/**
 * A clock that returns a constant value for now which can be set with calls to
 * {@link #set(Instant)}.
 *
 * <p>For uses of the {@link Clock} interface in unit tests.
 */
public class MockClock implements Clock {

  private Instant now;

  public static MockClock fromInstant(Instant initial) {
    return new MockClock(initial);
  }

  private MockClock(Instant initialNow) {
    this.now = initialNow;
  }

  public void set(Instant newNow) {
    checkArgument(!newNow.isBefore(now), "Cannot move MockClock backwards in time from %s to %s",
        now, newNow);
    this.now = newNow;
  }

  @Override
  public Instant now() {
    return now;
  }
}