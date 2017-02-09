//------------------------------------------------------------------------------------------------//
//                                                                                                //
//                                G l o b a l D e s c r i p t o r                                 //
//                                                                                                //
//------------------------------------------------------------------------------------------------//
// <editor-fold defaultstate="collapsed" desc="hdr">
//
//  Copyright © Hervé Bitteur and others 2000-2017. All rights reserved.
//
//  This program is free software: you can redistribute it and/or modify it under the terms of the
//  GNU Affero General Public License as published by the Free Software Foundation, either version
//  3 of the License, or (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
//  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
//  See the GNU Affero General Public License for more details.
//
//  You should have received a copy of the GNU Affero General Public License along with this
//  program.  If not, see <http://www.gnu.org/licenses/>.
//------------------------------------------------------------------------------------------------//
// </editor-fold>
package org.audiveris.omr.image;

import ij.process.ByteProcessor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class {@code GlobalDescriptor} describes a {@link GlobalFilter}
 *
 * @author Hervé Bitteur
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "global-filter")
public class GlobalDescriptor
        extends FilterDescriptor
{
    //~ Instance fields ----------------------------------------------------------------------------

    /** The threshold value for the whole pixel source. */
    @XmlAttribute(name = "threshold")
    public final int threshold;

    //~ Constructors -------------------------------------------------------------------------------
    /**
     * Creates a new GlobalDescriptor object.
     *
     * @param threshold Global threshold value
     */
    public GlobalDescriptor (int threshold)
    {
        this.threshold = threshold;
    }

    /** No-arg constructor meant for JAXB. */
    private GlobalDescriptor ()
    {
        threshold = 0;
    }

    //~ Methods ------------------------------------------------------------------------------------
    //------------//
    // getDefault //
    //------------//
    public static GlobalDescriptor getDefault ()
    {
        return new GlobalDescriptor(GlobalFilter.getDefaultThreshold());
    }

    //--------//
    // equals //
    //--------//
    @Override
    public boolean equals (Object obj)
    {
        if ((obj instanceof GlobalDescriptor) && super.equals(obj)) {
            GlobalDescriptor that = (GlobalDescriptor) obj;

            return this.threshold == that.threshold;
        }

        return false;
    }

    //-----------//
    // getFilter //
    //-----------//
    @Override
    public PixelFilter getFilter (ByteProcessor source)
    {
        return new GlobalFilter(source, threshold);
    }

    //
    //---------//
    // getKind //
    //---------//
    @Override
    public FilterKind getKind ()
    {
        return FilterKind.GLOBAL;
    }

    //----------//
    // hashCode //
    //----------//
    @Override
    public int hashCode ()
    {
        int hash = 5;
        hash = (53 * hash) + this.threshold;

        return hash;
    }

    //-----------------//
    // internalsString //
    //-----------------//
    @Override
    protected String internalsString ()
    {
        StringBuilder sb = new StringBuilder(super.internalsString());
        sb.append(" threshold: ").append(threshold);

        return sb.toString();
    }
}