package uk.ac.liv.oforero.metadata_it

object MetadataIT {
  import scalajutsu.experimental.metadata.plain.Metadata._

  def doStuff {
    assert( 5 == (5 addMeta ("serializable" -> true)))
    assert( (5 addMeta ("serializable" -> true)) == 5)
  }
}
