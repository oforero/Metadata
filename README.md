# Reflector: Scala Reflection Utilities

Early days.
Currently only supports companion objects and classes.

## Direct Usage

      class MyClass
      object MyClass

      val obj = companionOf[MyClass]
      val cls = companionObjectOf[MyClass]

OR

      def myMethod [C](implicit m: Manifest[C]) {
        val obj = companionOf(m)
        // do stuff with obj
      }

## Pattern Matching

      class MyClass
      object MyClass

      // match a class against companion objects
      classOf[MyClass] match {
        case Companion(`MyClass`) => // do something
        case _ => // no match
      }

      // extract companion objects
      classOf[MyClass] match {
        case Companion(obj) => // do something with obj
        case _ => // no match
      }

      // match a companion against classes
      val CLASS = classOf[MyClass]
      MyClass match {
        case Companion(CLASS) => // do something with
        case _ => // no match
      }

      // extract companion class
      MyClass match {
        case Companion(cls) => // do something with cls
        case _ => // no match
      }

## Pattern Matching against Type Parameters:

      @RunWith(classOf[JUnitRunner])
      class TypeParametersSpec extends FlatSpec with Assertions {

        val t0 = new C0
        val t1 = new C1(new T1)
        val t2 = new C2(new T1, new T2)
        val t3 = new C3(new T1, new T2, new T3)

        val t1w2t3 = new C3(new T1, new W2, new T3)

        /** Test for Type Parameters **/
        "Matching against the type parameters" should "find the type parameter class" in {
          t1 match {
            case TypeParameters(p1) ⇒ assert(p1 === classOf[T1])
            case _ ⇒ fail
          }


          val TypeParameters(p1, p2) = t2
          assert((p1, p2) === (classOf[T1], classOf[T2]))
        }

      }

## License

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:


1. Redistribution in source code form must retain the above copyright
   notice, this list of conditions and the following disclaimer.


2. Redistribution in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.


3. The name of the author may not be used to endorse or promote products
   derived from this software without specific prior written permission.


THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.