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
package scalajutsu.experimental.metadata.plain

import scalajutsu.experimental.metadata.{MetadataCompanion, Metadata ⇒ TMeta}
/**
 * TODO: Write description here!!
 *
 * @author Oscar Forero
 * @version 1.0
 *  
 */

class Metadata[T](val value: T) extends TMeta[T] {
  protected var metadata = Map[String, Any]()
}

object Metadata extends MetadataCompanion {
  protected def builder[T](value: T): Metadata[T] = new Metadata(value)
}