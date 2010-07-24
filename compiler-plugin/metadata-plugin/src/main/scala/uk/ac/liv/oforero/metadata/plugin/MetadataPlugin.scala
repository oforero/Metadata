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

package uk.ac.liv.oforero.metadata.plugin

/**
 * TODO: Write description here!!
 *
 * @author Oscar Forero
 * @version 1.0
 * 
 * Date: Apr 18, 2010
 * Time: 5:33:58 PM
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