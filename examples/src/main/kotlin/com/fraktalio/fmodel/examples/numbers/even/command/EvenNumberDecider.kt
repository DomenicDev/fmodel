/*
 * Copyright (c) 2021 Fraktalio D.O.O. All rights reserved.
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

package com.fraktalio.fmodel.examples.numbers.even.command

import com.fraktalio.fmodel.domain.Decider
import com.fraktalio.fmodel.examples.numbers.api.*

/**
 * Even number decider - pure declaration of our program logic
 *
 * @return Even number decider instance
 */
fun evenNumberDecider(): Decider<NumberCommand.EvenNumberCommand?, EvenNumberState, NumberEvent.EvenNumberEvent?> =
    Decider(
        isTerminal = { s -> s.value.get > 100 },
        initialState = EvenNumberState(Description("Initial state"), NumberValue(0)),
        decide = { c, s ->
            when (c) {
                is NumberCommand.EvenNumberCommand.AddEvenNumber -> listOf(
                    NumberEvent.EvenNumberEvent.EvenNumberAdded(
                        c.description,
                        c.value
                    )
                )
                is NumberCommand.EvenNumberCommand.SubtractEvenNumber -> listOf(
                    NumberEvent.EvenNumberEvent.EvenNumberSubtracted(
                        c.description,
                        c.value
                    )
                )
                else -> emptyList()
            }
        },
        evolve = { s, e ->
            when (e) {
                is NumberEvent.EvenNumberEvent.EvenNumberAdded -> EvenNumberState(
                    e.description,
                    NumberValue(s.value.get + e.value.get)
                )
                is NumberEvent.EvenNumberEvent.EvenNumberSubtracted -> EvenNumberState(
                    e.description,
                    NumberValue(s.value.get - e.value.get)
                )
                else -> s
            }
        }
    )
