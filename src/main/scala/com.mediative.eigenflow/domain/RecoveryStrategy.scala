/*
 * Copyright 2016 Mediative
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mediative.eigenflow.domain

import scala.concurrent.duration.FiniteDuration

sealed trait RecoveryStrategy

object RecoveryStrategy {
  case class Retry(interval: FiniteDuration, attempts: Int) extends RecoveryStrategy

  case object Fail extends RecoveryStrategy

  case object Complete extends RecoveryStrategy

  def default: PartialFunction[Throwable, RecoveryStrategy] = {
    case _ => Fail
  }
}
