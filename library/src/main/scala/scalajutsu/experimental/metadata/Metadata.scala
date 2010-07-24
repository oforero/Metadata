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

package scalajutsu.experimental.metadata

/**
 * TODO: Write description here!!
 *
 * @author Oscar Forero
 * @version 1.0
 * 
 * Date: Apr 18, 2010
 * Time: 3:47:46 PM
 * 
 */


trait Metadata[T] {
  protected var metadata: Map[String, Any]
  protected[metadata] val value: T

  def addMeta(meta: (String, Any)): Metadata[T] = {
    metadata += meta
    this
  }

  def hasMeta = ! metadata.isEmpty 
  def hasMeta(key: String) = metadata.contains(key)

  override def equals(other: Any) = other match {
    case other: Metadata[t] => this.value == other.value
    case _ => value == other
  }

  override lazy val hashCode = value.hashCode
}



abstract class MetadataCompanion {

  protected def builder[T](value: T): Metadata[T]

  implicit def any2Metadata[T](value: T): Metadata[T] = builder(value)
  implicit def meta2Any[T](obj: Metadata[T]): T = obj.value
}

