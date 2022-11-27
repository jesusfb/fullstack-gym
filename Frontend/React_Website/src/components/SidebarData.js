import React from "react";
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';
import * as IcoIcons from 'react-icons/im';
import * as TfiIcons from 'react-icons/tfi';

export const SidebarData = [
    {
        title : 'Courses',
        path : '/Courses',
        icon : <AiIcons.AiFillHome />,
        iconOpened : <IoIcons.IoIosArrowDropup />,
        iconClosed : <IoIcons.IoIosArrowDropdownCircle />,
        subNav: [
            {
                title : 'Free Group Courses',
                path : '/FreeGroupCourses',
                icon : <TfiIcons.TfiLayoutGrid3 />
            },
            {
                title : 'Premium Group Courses',
                path : '/PremiumGroupCourses',
                icon : <TfiIcons.TfiLayoutGrid3Alt />
            }
        ]
    },
    {
        title : 'About Page',
        path : '/aboutPage',
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