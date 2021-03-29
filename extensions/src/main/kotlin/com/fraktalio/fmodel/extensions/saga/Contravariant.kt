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

package com.fraktalio.fmodel.extensions.saga

import arrow.Kind2

/**
 * Contravariant
 *
 * Every contravariant functor gives rise to an invariant functor, by ignoring the `f` function.
 *
 * @param F
 */
interface Contravariant<F> : InvariantAR<F> {

    // ############ (AR) Action result ############
    fun <AR, ARn, A> Kind2<F, AR, A>.lmapOnAR(f: (ARn) -> AR): Kind2<F, ARn, A>

    override fun <AR, ARn, A> Kind2<F, AR, A>.imapOnAR(f: (AR) -> ARn, g: (ARn) -> AR): Kind2<F, ARn, A> =
        lmapOnAR(g)

}
