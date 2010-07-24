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
package scalajutsu.experimental.metadata.plugin

/**
 * TODO: Write description here!!
 *
 * @author Oscar Forero
 * @version 1.0
 * 
 */

import scala.tools.nsc
import nsc.plugins.{PluginComponent, Plugin}
import nsc.transform.Transform
import nsc.{Phase, Global}


class MetadataPlugin(val global: Global) extends Plugin {
  import global._

  val name = "metadata"
  val description = "assure that plain objects are never at the left side of a comparison with Metadata wrapped objects"
  val components = List[PluginComponent](MetadataComponent)

  private object MetadataComponent extends PluginComponent with Transform {
    val global: MetadataPlugin.this.global.type = MetadataPlugin.this.global

    // Using the Scala Compiler 2.8.x the runsAfter should be written as below
    val runsAfter = List[String]("typer");
    val phaseName = MetadataPlugin.this.name
    def newTransformer(unit: global.CompilationUnit) = MetadataPhase

    object MetadataPhase extends global.Transformer {
      private def swapTree(app: Apply, sel: Select, left: Tree, right: Tree) = {
        val newSel = sel.copy(qualifier = right)
        newSel.tpe = sel.tpe
        newSel.symbol = sel.symbol
        newSel.pos = sel.pos

        val newTree = Apply(newSel, List(left))
        newTree.tpe = app.tpe
        newTree.symbol = app.symbol
        newTree.pos = app.pos
        super.transform(newTree)
      }

      object Name {
        def unapply(name: Name) = Some(name.toString)
      }

      override def transform(tree: Tree) = {
        tree match {
          case old@Apply(sel@Select(left,  Name("$eq$eq")), List(right)) ⇒ {
            if((left.tpe ne right.tpe) && right.tpe.toString.startsWith("uk.ac.liv.oforero.metadata.Metadata")) {
//              val newTree: Apply = swapTree(old, sel, left, right)
//              println("Processed tree: " + newTree)
//              newTree
              println("swapping " + name.getClass)
              swapTree(old, sel, left, right)
            } else {
              super.transform(old)
            }
          }
          case t ⇒ {
            t match {
              case old@Apply(sel, List(right)) ⇒ {
                println("Processing tree: " + old)
                println("tree has: " + sel.getClass)
                old
              }
              case x ⇒ x
            }
            super.transform(t)
          }
        }
      }
    }
  }
}