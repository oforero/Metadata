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
 * Base trait for a Metadata class
 *
 * @author Oscar Forero
 * @version 1.0
 * 
 */
trait Metadata[T] {
  protected var metadata: Map[String, Any]
  protected[metadata] val value: T

  /**
   * Add an object to the metadata map
   */
  def addMeta(meta: (String, Any)): Metadata[T] = {
    metadata += meta
    this
  }

  /**
   * True if it has any metadata in the map
   */
  def hasMeta = ! metadata.isEmpty

  /**
   * True if the key exists in the map
   */
  def hasMeta(key: String) = metadata.contains(key)

  /**
   * Equals implementation making the metadata disappear
   */
  override def equals(other: Any) = other match {
    case other: Metadata[t] => this.value == other.value
    case _ => value == other
  }


  /**
   * hashCode implementation making the metadata disappear
   */
  override lazy val hashCode = value.hashCode
}


/**
 * Base class for metadata companions proving the implicit conversions
 *
 * @author Oscar Forero
 * @version 1.0
 *
 */
abstract class MetadataCompanion {

  protected def builder[T](value: T): Metadata[T]

  implicit def any2Metadata[T](value: T): Metadata[T] = builder(value)
  implicit def meta2Any[T](obj: Metadata[T]): T = obj.value
}

