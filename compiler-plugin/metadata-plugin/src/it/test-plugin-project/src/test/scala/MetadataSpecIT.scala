/*
 *    _   _       _               __  __ ____           ____  _____
 *   | | | | ___ | |             |  \/  / ___|  ___    / ___|| ____|_ __   __ _
 *   | | | |/ _ \| |      _____  | |\/| \___ \ / __|   \___ \|  _| | '_ \ / _` |
 *   | |_| | (_) | |___  |_____| | |  | |___) | (__ _   ___) | |___| | | | (_| |_
 *    \___/ \___/|_____|         |_|  |_|____/ \___(_) |____/|_____|_| |_|\__, (_)
 *                                                                        |___/
 *
 *   Copyright (c) 2010. Oscar Forero
 *   This code is being developed for the partial fulfilment of the requirements
 *   for the degree of MSc. in Software Engineering at the University of Liverpool.
 *   Any total or partial reproduction of this code is strictly forbidden,
 *   unless is related to the creation or assessment of this project.
 *
 *   Tittle: Development of a Financial Domain Specific Language for the Java Virtual Machine in Scala & Clojure
 *   Student: Oscar Mauricio Forero Carrillo
 *   Student ID: 15112165
 *   Email: oscar.forerocarrillo@my.ohecampus.com
 *   Dissertation Adviser: Dr. Yanguo Jing
 */

package uk.ac.liv.oforero.metadata_it

import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner

/**
 * TODO: Write description here!!
 *
 * @author Oscar Forero
 * @version 1.0
 *
 * Date: Apr 18, 2010
 * Time: 4:33:43 PM
 *
 */

@RunWith(classOf[JUnitRunner])
class MetadataSpec extends WordSpec with ShouldMatchers {
  "An object" when {
    "enriched with metadata" should {
      import uk.ac.liv.oforero.metadata.plain.Metadata._
      val a = 5 addMeta ("serializable" → true)

      "be equal to other without metadata" in {
        assert( a == 5 )
        assert( a.hasMeta)
        assert( a hasMeta "serializable")
        assert( 5 == a)
        var c = 3
        assert( c + 2 == a)
      }

      "be assignable to another variable of the underlying type" in {
        val b: Int = a
        assert( a == b )
        assert( b == a )
      }
    }
  }
}
