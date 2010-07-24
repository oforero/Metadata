/*
 *      _____            __          _        __
 *     / ___/_________ _/ /____ _   (_)__  __/ /________  __
 *     \__ \/ ___/ __ `/ // __ `/  / // / / / __/ ___/ / / /
 *    ___/ / /__/ /_/ / // /_/ /  / // /_/ / /_(__  ) /_/ /
 *   /____/\___/\__,_/_/ \__,_/__/ / \__,_/\__/____/\__,_/
 *                            /___/
 *
 *   Copyright (c) 2010, Oscar Forero & Scalajutsu Contributors
 *   All rights reserved.
 *
 */
package scalajutsu.experimental.metadata_it

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
