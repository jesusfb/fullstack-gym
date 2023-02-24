import React from "react";
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';
import * as IcoIcons from 'react-icons/im';
import * as TfiIcons from 'react-icons/tfi';
import * as GiIcons from 'react-icons/gi';
import * as BsIcons from "react-icons/bs";

export const SidebarData = [
    {
        title : 'Plans',
        path : '/Plans',
        icon : <BsIcons.BsFillPinAngleFill />,
        cName : 'nav-text'
    },
    {
        title : 'Courses',
        icon : <AiIcons.AiFillHome />,
        iconOpened : <IoIcons.IoIosArrowDropup />,
        iconClosed : <IoIcons.IoIosArrowDropdownCircle />,
        subNav: [
            {
                title : 'Gold Membership',
                path : '/GoldMember',
                icon : <GiIcons.GiGoldBar />
            },
            {
                title : 'Silver Membership',
                path : '/SilverMember',
                icon : <TfiIcons.TfiLayoutGrid3Alt />
            },
            {
                title : 'Bronze Membership',
                path : '/BronzeMember',
                icon : <TfiIcons.TfiLayoutGrid3 />
            }
        ]
    },
    {
        title : 'About Page',
        path : '/AboutPage',
        icon : <AiIcons.AiOutlineInfoCircle />,
        cName : 'nav-text'
    },
    {
        title : 'Our Team',
        path : '/ourTeam',
        icon : <IcoIcons.ImUsers />,
        cName : 'nav-text'
    }
]

export default SidebarData;