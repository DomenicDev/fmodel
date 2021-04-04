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

package com.fraktalio.fmodel.examples.numbers

import arrow.core.Either
import com.fraktalio.fmodel.examples.numbers.api.Description
import com.fraktalio.fmodel.examples.numbers.api.NumberCommand
import com.fraktalio.fmodel.examples.numbers.api.NumberValue
import com.fraktalio.fmodel.examples.numbers.even.command.evenNumberDecider
import com.fraktalio.fmodel.examples.numbers.even.query.evenNumberView
import com.fraktalio.fmodel.examples.numbers.odd.command.oddNumberDecider
import com.fraktalio.fmodel.examples.numbers.odd.query.oddNumberView
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking { // start main coroutine

    val eventSourcingAggregate = numberAggregate(evenNumberDecider(), oddNumberDecider(), numberRepository())
    val materializedView = numberMaterializedView(oddNumberView(), evenNumberView(), NumberViewRepository())

    println(
        eventSourcingAggregate.handle(
            Either.Right(
                NumberCommand.OddNumberCommand.AddOddNumber(
                    Description("Add 1"),
                    NumberValue(1)
                )
            )
        ).map { it.map { eventStoredSuccessfully -> materializedView.handle(eventStoredSuccessfully.event) } }
    )
    println(
        eventSourcingAggregate.handle(
            Either.Left(
                NumberCommand.EvenNumberCommand.AddEvenNumber(
                    Description("Add 4"),
                    NumberValue(4)
                )
            )
        ).map { it.map { eventStoredSuccessfully -> materializedView.handle(eventStoredSuccessfully.event) } }
    )
    println(
        eventSourcingAggregate.handle(
            Either.Right(
                NumberCommand.OddNumberCommand.AddOddNumber(
                    Description("Add 3"),
                    NumberValue(3)
                )
            )
        ).map { it.map { eventStoredSuccessfully -> materializedView.handle(eventStoredSuccessfully.event) } }
    )
    println(
        eventSourcingAggregate.handle(
            Either.Left(
                NumberCommand.EvenNumberCommand.AddEvenNumber(
                    Description("Add 8"),
                    NumberValue(8)
                )
            )
        ).map { it.map { eventStoredSuccessfully -> materializedView.handle(eventStoredSuccessfully.event) } }
    )
    println(
        eventSourcingAggregate.handle(
            Either.Left(
                NumberCommand.EvenNumberCommand.SubtractEvenNumber(
                    Description("Subtract 2"),
                    NumberValue(2)
                )
            )
        ).map { it.map { eventStoredSuccessfully -> materializedView.handle(eventStoredSuccessfully.event) } }
    )
}

